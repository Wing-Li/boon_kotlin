package com.lyl.boon.ui.joke;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.lyl.boon.R;
import com.lyl.boon.utils.ImgUtils;
import com.lyl.boon.net.Network;
import com.lyl.boon.app.MyApp;
import com.lyl.boon.net.entity.ZhuangbiEntity;
import com.lyl.boon.ui.base.apdter.MyBaseAdapter;

import org.byteam.superadapter.internal.SuperViewHolder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Author: lyl
 * Date Created : 2018/2/10.
 */
public class JokeListAdapter extends MyBaseAdapter<ZhuangbiEntity> {

    private Context context;

    public JokeListAdapter(Context context, List<ZhuangbiEntity> items, int layoutResId) {
        super(context, items, layoutResId);
        this.context = context;
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int position, ZhuangbiEntity item) {
        super.onBind(holder, viewType, position, item);

        holder.setText(R.id.item_grid_title, item.getDescription());

        final String url = item.getImage_url();

        ImgUtils.INSTANCE.load(context, url, (ImageView) holder.getView(R.id.item_grid_img));
        String[] split = url.split("/");
        final String imgName = split[split.length - 1];

        holder.getView(R.id.item_grid_img).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(getMContext()).setTitle(context.getString(R.string.image_save))//
                        .setMessage(context.getString(R.string.image_save_msg))//
                        .setNegativeButton(context.getString(R.string.save), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                downloadImage(url, imgName);
                            }
                        })//
                        .setPositiveButton(context.getString(R.string.cancel), null).create().show();
                return false;
            }
        });
    }

    private void downloadImage(String url, final String imgName) {
        Call<ResponseBody> responseBodyCall = Network.INSTANCE.getZhuangbi().downloadFileWithDynamicUrlSync(url);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    File imgFile = new File(MyApp.Companion.getAppPath() + File.separator + imgName);
                    boolean writtenToDisk = writeResponseBodyToDisk(response.body(), imgName, imgFile);
                    if (writtenToDisk) {
                        getMContext().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(imgFile)));
                        Toast.makeText(getMContext(), R.string.save_success, Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getMContext(), R.string.save_fail, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getMContext(), R.string.down_error, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getMContext(), R.string.down_error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean writeResponseBodyToDisk(ResponseBody body, String imgName, File imgFile) {
        try {
            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[1024];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(imgFile);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;
                }

                outputStream.flush();

                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }
}
