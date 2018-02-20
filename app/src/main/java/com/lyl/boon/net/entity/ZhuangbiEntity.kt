package com.lyl.boon.net.entity

/**
 * Author: lyl
 * Date Created : 2018/2/10.
 */
class ZhuangbiEntity : BaseEntity() {

    /**
     * id : 551
     * description : 装逼如风常伴我身
     * path :
     * size : 0
     * width : 284
     * height : 199
     * created_at : 2016-01-27 23:22:50
     * updated_at : 2016-01-27 23:23:42
     * user_id : 1
     * permitted_at : 2016-01-27 23:23:42
     * disk : qiniu
     * hotpoint : 295
     * channel : admin
     * upload_id : 1658
     * image_url : http://7xjzdd.com1.z0.glb.clouddn.com/i/2016-01-27-bd6f1e1e31fea64f7f2f33ba31b0b1a2.gif
     * file_size : 1.91 MB
     * upload : {"id":1658,"name":null,"description":"装逼如风常伴我身","disk":"qiniu","path":"i/2016-01-27-bd6f1e1e31fea64f7f2f33ba31b0b1a2.gif","size":2005559,
     * "user_id":1,"created_at":"2016-01-27 23:22:50","updated_at":"2016-01-27 23:23:42","uploadable_id":null,"uploadable_type":null,"url":"http://7xjzdd
     * .com1.z0.glb.clouddn.com/i/2016-01-27-bd6f1e1e31fea64f7f2f33ba31b0b1a2.gif"}
     */
    var id: Int = 0
    var description: String? = null
    var path: String? = null
    var size: Int = 0
    var width: Int = 0
    var height: Int = 0
    var created_at: String? = null
    var updated_at: String? = null
    var user_id: Int = 0
    var permitted_at: String? = null
    var disk: String? = null
    var hotpoint: Int = 0
    var channel: String? = null
    var upload_id: Int = 0
    var image_url: String? = null
    var file_size: String? = null
    /**
     * id : 1658
     * name : null
     * description : 装逼如风常伴我身
     * disk : qiniu
     * path : i/2016-01-27-bd6f1e1e31fea64f7f2f33ba31b0b1a2.gif
     * size : 2005559
     * user_id : 1
     * created_at : 2016-01-27 23:22:50
     * updated_at : 2016-01-27 23:23:42
     * uploadable_id : null
     * uploadable_type : null
     * url : http://7xjzdd.com1.z0.glb.clouddn.com/i/2016-01-27-bd6f1e1e31fea64f7f2f33ba31b0b1a2.gif
     */

    var upload: UploadBean? = null

    class UploadBean {
        var id: Int = 0
        var name: Any? = null
        var description: String? = null
        var disk: String? = null
        var path: String? = null
        var size: Int = 0
        var user_id: Int = 0
        var created_at: String? = null
        var updated_at: String? = null
        var uploadable_id: Any? = null
        var uploadable_type: Any? = null
        var url: String? = null
    }

}
