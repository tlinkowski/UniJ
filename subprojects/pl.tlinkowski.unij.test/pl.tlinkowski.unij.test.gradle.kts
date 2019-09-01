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
config {
  groovydoc {
    enabled = true
    replaceJavadoc = true
  }
}

dependencies {
  val groovyVersion: String by project // https://groovy-lang.org/
  val spockVersion: String by project // http://spockframework.org/
  val slf4jVersion: String by project // https://www.slf4j.org/

  api(project(":pl.tlinkowski.unij.api"))
  api(project(":pl.tlinkowski.unij.service.api"))
  api(group = "org.codehaus.groovy", name = "groovy-all", version = groovyVersion)
  api(group = "org.spockframework", name = "spock-core", version = spockVersion)
  runtimeOnly(group = "org.slf4j", name = "slf4j-simple", version = slf4jVersion)
}

//region JAVA PLATFORM MODULE SYSTEM
val moduleName by extra("pl.tlinkowski.unij.test")

tasks {
  jar {
    inputs.property("moduleName", moduleName)

    manifest {
      attributes["Automatic-Module-Name"] = moduleName
    }
  }
}
//endregion
