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
    description = "Universal facade of JDK 9+ API, focused on Collection factory methods"
    tags = listOf("java", "jdk", "facade", "collection", "collector", "immutability", "backport")
    inceptionYear = "2018"

    links {
      website = "https://github.com/tlinkowski/UniJ"
      issueTracker = "$website/issues"
      scm = "$website.git"
    }
  }
}

subprojects {
  // https://docs.gradle.org/current/userguide/java_library_plugin.html
  apply<JavaLibraryPlugin>()

  // https://github.com/melix/jmh-gradle-plugin
  apply<JMHPlugin>()

  //region CONFIGURATION PROPERTIES
  val api by configurations
  val implementation by configurations
  val compileOnly by configurations
  val runtimeOnly by configurations
  val annotationProcessor by configurations

  val testImplementation by configurations
  //endregion

  dependencies {
    val basicAnnotationsVersion: String by project // https://github.com/tlinkowski/basic-annotations

    compileOnly(group = "pl.tlinkowski.annotation", name = "pl.tlinkowski.annotation.basic", version = basicAnnotationsVersion)
  }

  config {
    javadoc.autoLinks {
      configurations = listOf("api", "implementation", "compileOnly")
    }
  }

  //region PER-SUBPROJECT-TYPE CONFIGURATION
  if (name.contains(".sample.")) {
    config {
      publishing.enabled = false
    }

    dependencies {
      val slf4jVersion: String by project // https://www.slf4j.org/
      runtimeOnly(group = "org.slf4j", name = "slf4j-simple", version = slf4jVersion)
    }

    tasks {
      "jacocoTestCoverageVerification" {
        enabled = false
      }
    }
  } else {
    config {
      bintray.enabled = true
    }
  }

  if (name.contains(".bundle.")) {
    dependencies {
      api(project(":pl.tlinkowski.unij.api"))
      testImplementation(project(":pl.tlinkowski.unij.test"))
    }
    //region TEMPORARY WORKAROUND: https://github.com/tlinkowski/UniJ/issues/40
    tasks {
      val simulateJavadoc by registering {
        val javadocDir = buildDir.resolve("docs/javadoc")
        val elementList = javadocDir.resolve("element-list")

        group = "documentation"
        inputs.property("project.name", project.name)
        outputs.file(elementList)

        doFirst {
          javadocDir.mkdirs()
          elementList.writeText("module:${project.name}\n")
        }
      }
      "javadoc" {
        enabled = false
        finalizedBy(simulateJavadoc)
      }
    }
    //endregion
  }
  if (name.contains(".service.collect.") || name.contains(".service.misc.")) {
    dependencies {
      val autoServiceVersion: String by project // https://github.com/google/auto/tree/master/service

      compileOnly(group = "com.google.auto.service", name = "auto-service-annotations", version = autoServiceVersion)
      annotationProcessor(group = "com.google.auto.service", name = "auto-service", version = autoServiceVersion)

      implementation(project(":pl.tlinkowski.unij.service.api"))
      testImplementation(project(":pl.tlinkowski.unij.test"))
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

  tasks.withType<Jar> {
      duplicatesStrategy = DuplicatesStrategy.EXCLUDE
  }
}
