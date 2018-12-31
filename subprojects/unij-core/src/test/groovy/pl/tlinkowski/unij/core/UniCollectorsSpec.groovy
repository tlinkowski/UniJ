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

package pl.tlinkowski.unij.core

import spock.lang.Specification

/**
 * @author Tomasz Linkowski
 */
final class UniCollectorsSpec extends Specification {

  def "toUnmodifiableList"(List<Integer> list) {
    expect:
      list.stream().collect(UniCollectors.toUnmodifiableList()) == list
    where:
      list      | _
      []        | _
      [1]       | _
      [1, 2]    | _
      [1, 2, 3] | _
  }

}
