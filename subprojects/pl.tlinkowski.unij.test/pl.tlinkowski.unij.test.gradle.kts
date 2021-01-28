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
config {
  groovydoc {
    enabled = true
    replaceJavadoc = true
  }
}

superpom.isTestProject = true

//region REMOVE REDUNDANT KOTLIN-STDLIB DEPENDENCY ADDED BY SUPERPOM PLUGIN
afterEvaluate {
  configurations.api {
    dependencies.removeIf { it.name == "kotlin-stdlib-jdk8" }
  }
}
//endregion

dependencies {
  val slf4jVersion: String by project // https://www.slf4j.org/

  api(project(":pl.tlinkowski.unij.service.api"))
  implementation(project(":pl.tlinkowski.unij.api")) // for UniJBundleTest
  runtimeOnly(group = "org.slf4j", name = "slf4j-simple", version = slf4jVersion)
}
