/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2018-2019 Tomasz Linkowski.
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
package pl.tlinkowski.unij.sample.enduser.jdk11;

import java.util.List;

import pl.tlinkowski.unij.api.UniLists;

/**
 * @author Tomasz Linkowski
 */
public final class EndUsage {

  public static void main(String[] args) {
    System.out.println(names());
  }

  public static List<String> names() {
    return UniLists.of("foo", "bar");
  }

  private EndUsage() {
  }
}
