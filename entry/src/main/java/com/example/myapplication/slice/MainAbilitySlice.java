/*
 * Copyright (C) 2020-21 Application Library Engineering Group
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.myapplication.slice;

import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import com.example.myapplication.ResourceTable;
import com.example.socialconnector.SocailConnect;


/**
 * @return 42
 */
public class MainAbilitySlice extends AbilitySlice {

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);

        SocailConnect s = (SocailConnect) findComponentById(ResourceTable.Id_image1);
        s.setContext(this, "https://www.facebook.com/");
        SocailConnect s1 = (SocailConnect) findComponentById(ResourceTable.Id_image2);
        s1.setContext(this, "https://www.instagram.com/");
        SocailConnect s2 = (SocailConnect) findComponentById(ResourceTable.Id_image3);
        s2.setContext(this, "https://web.whatsapp.com/");
        SocailConnect s3 = (SocailConnect) findComponentById(ResourceTable.Id_image4);
        s3.setContext(this, "https://www.linkedin.com/feed/");
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}
