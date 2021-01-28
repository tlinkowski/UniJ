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
package pl.tlinkowski.unij.sample.lib;

import java.util.*;

import pl.tlinkowski.unij.api.*;

/**
 * @author Tomasz Linkowski
 */
public final class SampleLogic {

  private final Map<String, String> map = UniMaps.of(
          "a", "b",
          "c", "de"
  );

  public static int fooBar() {
    return new SampleLogic().foo("bar");
  }

  /**
   * Computes {@code foo} from given {@code bar}.
   */
  public int foo(String bar) {
    String baz = replaceAll(bar);
    List<Integer> list = letters(baz);
    Set<Integer> set = UniSets.copyOf(list);
    return sum(set);
  }

  private String replaceAll(String string) {
    return map.entrySet().stream().reduce(
            string, (str, entry) -> str.replace(entry.getKey(), entry.getValue()), SampleLogic::combineUnsupported
    );
  }

  private static List<Integer> letters(String string) {
    return string.codePoints().boxed().collect(UniCollectors.filtering(
            Character::isLetter, UniCollectors.toUnmodifiableList()
    ));
  }

  private static int sum(Collection<Integer> ints) {
    return ints.stream().mapToInt(Integer::intValue).sum();
  }

  private static <T> T combineUnsupported(T t1, T t2) {
    throw new UnsupportedOperationException();
  }
}
