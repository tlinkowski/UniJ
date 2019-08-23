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

package pl.tlinkowski.unij.api

import spock.lang.Specification

/**
 * @author Tomasz Linkowski
 */
class UniSetsSpec extends Specification {

  def "copyOf"(Set<Integer> list) {
    expect:
      UniSets.copyOf(list) == Set.copyOf(list)
    where:
      list      | _
      []        | _
      [1]       | _
      [1, 2]    | _
      [1, 2, 3] | _
  }

  def "of(n=0)"() {
    expect:
      UniSets.of() == Set.of()
  }

  def "of(n=1)"() {
    expect:
      UniSets.of(1) == Set.of(1)
  }

  def "of(n=2)"() {
    expect:
      UniSets.of(1, 2) == Set.of(1, 2)
  }

  def "of(n=3)"() {
    expect:
      UniSets.of(1, 2, 3) == Set.of(1, 2, 3)
  }

  def "of(n=4)"() {
    expect:
      UniSets.of(1, 2, 3, 4) == Set.of(1, 2, 3, 4)
  }

  def "of(n=5)"() {
    expect:
      UniSets.of(1, 2, 3, 4, 5) == Set.of(1, 2, 3, 4, 5)
  }

  def "of(n=6)"() {
    expect:
      UniSets.of(1, 2, 3, 4, 5, 6) == Set.of(1, 2, 3, 4, 5, 6)
  }

  def "of(n=7)"() {
    expect:
      UniSets.of(1, 2, 3, 4, 5, 6, 7) == Set.of(1, 2, 3, 4, 5, 6, 7)
  }

  def "of(n=8)"() {
    expect:
      UniSets.of(1, 2, 3, 4, 5, 6, 7, 8) == Set.of(1, 2, 3, 4, 5, 6, 7, 8)
  }

  def "of(n=9)"() {
    expect:
      UniSets.of(1, 2, 3, 4, 5, 6, 7, 8, 9) == Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
  }

  def "of(n=10)"() {
    expect:
      UniSets.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10) == Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  }

  def "of(...)"() {
    expect:
      UniSets.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11) == Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)
  }
}
