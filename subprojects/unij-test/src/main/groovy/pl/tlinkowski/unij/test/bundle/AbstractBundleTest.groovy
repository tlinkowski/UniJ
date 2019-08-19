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

package pl.tlinkowski.unij.test.bundle

import spock.lang.Specification

import pl.tlinkowski.unij.api.UniLists
import pl.tlinkowski.unij.api.UniSets

/**
 * Test that should be extended by every bundle.
 *
 * This test verifies that a bundle has proper runtime dependencies.
 *
 * @author Tomasz Linkowski
 */
abstract class AbstractBundleTest extends Specification {

  def "UnmodifiableListFactory implementation found"() {
    when:
      UniLists.of()
    then:
      noExceptionThrown()
  }

  def "UnmodifiableSetFactory implementation found"() {
    when:
      UniSets.of()
    then:
      noExceptionThrown()
  }
}
