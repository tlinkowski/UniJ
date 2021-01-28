/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2018-2021 Tomasz Linkowski.
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
modularity.mixedJavaRelease(8)

superpom.useLombok = true

dependencies {
  val guavaLowVersion: String by project // https://github.com/google/guava

  compileOnly(group = "com.google.guava", name = "guava", version = guavaLowVersion)
  testRuntimeOnly(group = "com.google.guava", name = "guava", version = guavaLowVersion)
}
