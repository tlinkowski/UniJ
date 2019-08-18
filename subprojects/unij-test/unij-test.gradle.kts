/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2018 Tomasz Linkowski.
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
dependencies {
  compile project(":unij-api")
  runtimeOnly group: "org.slf4j", name: "slf4j-simple", version: slf4jVersion

  compile group: "org.codehaus.groovy", name: "groovy-all", version: "2.5.6"
  compile group: "org.spockframework", name: "spock-core", version: "1.3-groovy-2.5"
}
