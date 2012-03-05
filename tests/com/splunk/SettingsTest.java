/*
 * Copyright 2011 Splunk, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"): you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.splunk;

import org.junit.Test;

public class SettingsTest extends SplunkTestCase {
    @Test public void testSettings() {
        Service service = connect();
        Settings settings = service.getSettings();
        settings.getSplunkDB();
        settings.getSplunkHome();
        settings.getEnableSplunkWebSSL();
        settings.getHost();
        settings.getHttpPort();
        settings.getMgmtPort();
        settings.getMinFreeSpace();
        settings.getPass4SymmKey();
        settings.getServerName();
        String timeout = settings.getSessionTimeout(); // set aside original
        settings.getStartWebServer();
        settings.getTrustedIP();

        // test update
        Args args = new Args("sessionTimeout", "2h");
        settings.update(args);
        String updatedTimeout = settings.getSessionTimeout();
        assertEquals("Must be equal", updatedTimeout, "2h");
        // restore original timeout
        args.put("sessionTimeout", timeout);
        settings.update(args);
        updatedTimeout = settings.getSessionTimeout();
        assertEquals("Must be equal", updatedTimeout, timeout);
    }
}
