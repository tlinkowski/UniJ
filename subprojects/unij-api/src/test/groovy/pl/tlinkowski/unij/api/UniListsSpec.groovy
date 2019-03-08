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

package pl.tlinkowski.unij.api

import spock.lang.Specification

/**
 * @author Tomasz Linkowski
 */
final class UniListsSpec extends Specification {

  def "copyOf"(List<Integer> list) {
    expect:
      UniLists.copyOf(list) == List.copyOf(list)
    where:
      list      | _
      []        | _
      [1]       | _
      [1, 2]    | _
      [1, 2, 3] | _
  }

  def "of(n=0)"() {
    expect:
      UniLists.of() == List.of()
  }

  def "of(n=1)"() {
    expect:
      UniLists.of(1) == List.of(1)
  }

  def "of(n=2)"() {
    expect:
      UniLists.of(1, 2) == List.of(1, 2)
  }

  def "of(n=3)"() {
    expect:
      UniLists.of(1, 2, 3) == List.of(1, 2, 3)
  }

  def "of(n=4)"() {
    expect:
      UniLists.of(1, 2, 3, 4) == List.of(1, 2, 3, 4)
  }

  def "of(n=5)"() {
    expect:
      UniLists.of(1, 2, 3, 4, 5) == List.of(1, 2, 3, 4, 5)
  }

  def "of(n=6)"() {
    expect:
      UniLists.of(1, 2, 3, 4, 5, 6) == List.of(1, 2, 3, 4, 5, 6)
  }

  def "of(n=7)"() {
    expect:
      UniLists.of(1, 2, 3, 4, 5, 6, 7) == List.of(1, 2, 3, 4, 5, 6, 7)
  }

  def "of(n=8)"() {
    expect:
      UniLists.of(1, 2, 3, 4, 5, 6, 7, 8) == List.of(1, 2, 3, 4, 5, 6, 7, 8)
  }

  def "of(n=9)"() {
    expect:
      UniLists.of(1, 2, 3, 4, 5, 6, 7, 8, 9) == List.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
  }

  def "of(n=10)"() {
    expect:
      UniLists.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10) == List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  }

  def "of(...)"() {
    expect:
      UniLists.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11) == List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)
  }
}
