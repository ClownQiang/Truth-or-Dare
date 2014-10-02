package com.example.RealWordAndBigAdventure_Beta.tools;

import android.app.Activity;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.sso.QZoneSsoHandler;
import com.umeng.socialize.sso.RenrenSsoHandler;

/**
 * Created by clownqiang on 14-10-2.
 */
public class Utils {
    public static void shareSNS(String showText, Activity activity, int flag) {
        // 首先在您的Activity中添加如下成员变量
        final UMSocialService mController = UMServiceFactory.getUMSocialService("com.umeng.share");
        // 设置分享内容
        if (showText.isEmpty()) {
            mController.setShareContent("我在真心话大冒险中被整了，你也来加入吧！！！");
        } else if (!showText.isEmpty() && flag == Constant.BIGADVENTURE){
            mController.setShareContent("\"" + showText + "\"" + "，这个冒险你敢来吗？");
        } else if (!showText.isEmpty() && flag == Constant.REALWORLD){
            mController.setShareContent("\"" + showText + "\"" + "，这个真心话你敢回答吗？");
        }
        // 设置分享图片, 参数2为图片的url地址
        mController.setShareMedia(new UMImage(activity,
                "http://clownqiang.qiniudn.com/zxhdmxlogo.png"));
        mController.setAppWebSite(SHARE_MEDIA.RENREN, "http://shouji.baidu.com/game/item?docid=5078477&from=as");
        mController.getConfig().setPlatforms(SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QZONE, SHARE_MEDIA.RENREN);
        QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler(activity, Constant.QzoneAPPID, Constant.QzoneAPPKEY);
        qZoneSsoHandler.addToSocialSDK();
        RenrenSsoHandler renrenSsoHandler = new RenrenSsoHandler(activity,
                Constant.RRAPPID, Constant.RRAPPKEY,
                Constant.RRSECRETKEY);
        mController.getConfig().setSsoHandler(renrenSsoHandler);
        mController.openShare(activity, false);
    }
}
