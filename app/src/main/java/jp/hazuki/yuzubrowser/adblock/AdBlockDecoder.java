/*
 * Copyright (C) 2017 Hazuki
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package jp.hazuki.yuzubrowser.adblock;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdBlockDecoder {

    public static List<AdBlock> decode(String text, boolean comment) {
        List<AdBlock> adBlocks = new ArrayList<>();
        Scanner scanner = new Scanner(text);
        while (scanner.hasNext()) {
            String line = scanner.nextLine().trim();
            if (!TextUtils.isEmpty(line)) {
                if (comment && (line.charAt(0) == '#' || line.startsWith("//")))
                    continue;

                if (line.startsWith("127.0.0.1"))
                    line = line.replace("127.0.0.1", "h");

                adBlocks.add(new AdBlock(line));
            }
        }
        return adBlocks;
    }
}
