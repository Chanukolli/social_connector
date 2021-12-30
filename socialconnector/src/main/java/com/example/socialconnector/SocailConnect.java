package com.example.socialconnector;


import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.animation.AnimatorProperty;
import ohos.agp.components.AttrSet;
import ohos.agp.components.Component;
import ohos.agp.components.Image;
import ohos.app.Context;
import ohos.bundle.AbilityInfo;
import ohos.global.resource.NotExistException;
import ohos.global.resource.Resource;
import ohos.global.resource.ResourceManager;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;
import ohos.media.image.PixelMap;
import ohos.utils.net.Uri;

import java.io.IOException;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;


public class SocailConnect extends Image implements Component.ClickedListener, ohos.agp.render.render3d.Component {

    private static final String TAG = SocailConnect.class.getSimpleName();

    private static final int DOMAIN = 0xD000100;

    private static final HiLogLabel LABEL = new HiLogLabel(
            HiLog.LOG_APP, DOMAIN, TAG);

    private static final String DEFAULT_SOCIAL = "facebook";

    private int imageType = 0;

    private Context d;

    private Optional<PixelMap> pixelMapping;
    private Component component;

    public SocailConnect(Context context) {
        super(context);
    }

    public SocailConnect(Context context, AttrSet attrSet) {
        super(context, attrSet);
        initAttr(attrSet);
        setClickedListener(this);
    }

    private void initAttr(AttrSet attrSet) {

        checkImagetype();
    }

    private void checkImagetype() {
        switch (imageType) {
            case 0:
                pixelMapping = getPixelMapByResId(ResourceTable.Media_facebook);
                break;
            case 1:
                pixelMapping = getPixelMapByResId(ResourceTable.Media_whatsapp);
                break;
            case 2:
                pixelMapping = getPixelMapByResId(ResourceTable.Media_Twitter);
                break;
            case 3:
                pixelMapping = getPixelMapByResId(ResourceTable.Media_LinkedIN);
                break;
            default:
                pixelMapping = getPixelMapByResId(ResourceTable.Media_facebook);
        }
    }

    private Optional<PixelMap> getPixelMapByResId(final int resourceId) {
        final ResourceManager resourceManager = getContext().getResourceManager();
        Optional<PixelMap> pixelMappingOptional = Optional.empty();
        if (resourceManager == null) {
            return Optional.empty();
        }
        try (Resource resource = resourceManager.getResource(resourceId)) {
            if (resource == null) {
                HiLog.error(LABEL, "get pixelmap failed, get resource by id is null");
                return Optional.empty();
            }
            pixelMappingOptional = Util.preparePixelmap(resource);
        } catch (NotExistException e) {
            HiLog.error(LABEL, "close output failed NotExistException");
        } catch (IOException e) {
            HiLog.error(LABEL, "close output failed IOException");
        }
        return pixelMappingOptional;
    }


    @Override
    public void onClick(Component component) {
        this.component = component;
        Timer time = new Timer();

        time.schedule(new TimerTask() {
            @Override
            public void run() {
                  launch();
            }
        },1500);
            AnimatorProperty animatorProperty = component.createAnimatorProperty();
            animatorProperty.rotate(360).setDelay(500).setDuration(1500).setLoopedCount(2);
            animatorProperty.start();
    }

    public void launch()
    {
        Intent intent = new Intent();
        String URI = "https://appgallery.cloud.huawei.com/appDetail?pkgName=";

        Operation operation = new Intent.OperationBuilder()
                .withUri(Uri.parse(URI + "com.enrique.apprater"))
                .build();
        intent.setOperation(operation);
        getC().startAbility(intent, AbilityInfo.AbilityType.WEB.ordinal());
    }

    public void setContext(Context c)
    {
        this.d=c;
    }

    public Context getC()
    {
        return d;
    }
}
