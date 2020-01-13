/*
 * Copyright 2014-2020 Real Logic Limited.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Checksums can be added to recordings so that file corruption can be detected.
 * <p>
 * Recording can be checksumed as they are recorded or later via {@link io.aeron.archive.ArchiveTool} checksum methods.
 * Checksums are generated via implementations of {@link io.aeron.archive.checksum.Checksum} interface.
 */
package io.aeron.archive.checksum;