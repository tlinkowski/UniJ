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
import me.champeau.gradle.JMHPlugin
import me.champeau.gradle.JMHPluginExtension

plugins {
  // https://github.com/tlinkowski/tlinkowski-superpom
  id("pl.tlinkowski.gradle.my.superpom")

  // https://github.com/melix/jmh-gradle-plugin
  id("me.champeau.gradle.jmh") version "0.4.8" apply false
}

config {
  info {
    name = "UniJ"
    description = "Universal cross-JDK API provider for immutable collections"
    tags = listOf("java", "facade", "collection", "collector", "immutability")
    inceptionYear = "2018"

    links {
      website = "https://github.com/tlinkowski/UniJ"
      issueTracker = "$website/issues"
      scm = "$website.git"
    }
  }
}

subprojects {
  apply {
    // https://docs.gradle.org/current/userguide/java_library_plugin.html
    plugin(JavaLibraryPlugin::class)

    // https://github.com/melix/jmh-gradle-plugin
    plugin(JMHPlugin::class)
  }

  //region CONFIGURATION PROPERTIES
  val api by configurations
  val implementation by configurations
  val compileOnly by configurations
  val annotationProcessor by configurations

  val testImplementation by configurations
  val testCompileOnly by configurations
  //endregion

  configurations {
    testCompileOnly.extendsFrom(compileOnly)
  }

  dependencies {
    // https://projectlombok.org/changelog
    val lombokVersion: String by project
    compileOnly(group = "org.projectlombok", name = "lombok", version = lombokVersion)
    annotationProcessor(group = "org.projectlombok", name = "lombok", version = lombokVersion)

    val basicAnnotationsVersion: String by project
    compileOnly(group = "pl.tlinkowski.annotation", name = "basic-annotations", version = basicAnnotationsVersion)
  }

  config {
    javadoc.autoLinks {
      excludes.add("lombok-.*") // https://github.com/tlinkowski/UniJ/issues/38
      excludes.add("unij-.*") // https://github.com/aalmiray/kordamp-gradle-plugins/issues/169
    }
  }

  //region PER-SUBPROJECT-TYPE CONFIGURATION
  if (name.startsWith("unij-bundle-")) {
    dependencies {
      api(project(":unij-api"))
      testImplementation(project(":unij-test"))
    }
  }
  if (name.startsWith("unij-collect-") || name.startsWith("unij-misc-")) {
    dependencies {
      implementation(project(":unij-service-api"))
      testImplementation(project(":unij-test"))
    }
  }
  //endregion

  /**
   * BENCHMARKS
   * http://openjdk.java.net/projects/code-tools/jmh/
   */
  configure<JMHPluginExtension> {
    /*
     * Note that when using "separateClasspathJAR" option and running the benchmark on Windows,
     * the following four resources must be on the same drive (otherwise, "'other' has different root" is thrown):
     * 1) this project
     * 2) JDK
     * 3) Gradle user home dir (can be set using GRADLE_USER_HOME environment variable)
     * 4) TEMP dir (can be set using System properties through "java.io.tmpdir")
     */
    jvmArgsAppend = listOf("-Djmh.separateClasspathJAR=true") // https://bugs.openjdk.java.net/browse/CODETOOLS-7902106
  }
}

//region TEMPORARY workaround for: https://github.com/tlinkowski/UniJ/issues/40 (TODO: remove this)
val namesOfYetEmptyProjects = listOf(
        "unij-bundle-eclipse-jdk8",
        "unij-bundle-guava-jdk8",
        "unij-bundle-jdk8",
        "unij-bundle-jdk11",
        "unij-collect-eclipse",
        "unij-collect-guava",
        "unij-collect-jdk8",
        "unij-misc-jdk8",
        "unij-misc-jdk11"
)
namesOfYetEmptyProjects.forEach {
  project(":$it") {
    tasks {
      "javadoc" {
        enabled = false
      }
    }
  }
}
//endregion
