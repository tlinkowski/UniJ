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

package pl.tlinkowski.unij.sample.lib.usage.eclipse

import spock.lang.Specification

import pl.tlinkowski.unij.sample.lib.SampleLogic

/**
 * @author Tomasz Linkowski
 */
class SampleLogicSpec extends Specification {

  def foo() {
    expect:
      SampleLogic.fooBar() == 212
  }
}
