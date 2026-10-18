/*
 * Copyright 2023 The TensorFlow Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *             http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.mediapipe.examples.samples.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.mediapipe.examples.samples.MainViewModel
import com.google.mediapipe.examples.samples.R
import com.google.mediapipe.examples.samples.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonModelMap = mapOf(
            binding.btnHolisticLandmarker to Pair("holistic_landmarker.task", "Holistic Landmarker"),
            binding.btnPoseLandmarker to Pair("pose_landmarker_lite.task", "Pose Landmarker"),
            binding.btnFaceDetector to Pair("face_landmarker.task", "Face Detector"),
            binding.btnFaceLandmarker to Pair("face_landmarker.task", "Face Landmarker"),
            binding.btnHandLandmarker to Pair("hand_landmarker.task", "Hand Landmarker"),
            binding.btnGestureRecognizer to Pair("gesture_recognizer.task", "Gesture Recognizer"),
            binding.btnObjectDetection to Pair("holistic_landmarker.task", "Object Detection"),
            binding.btnImageClassification to Pair("holistic_landmarker.task", "Image Classification")
        )

        buttonModelMap.forEach { (button, pair) ->
            button.setOnClickListener {
                viewModel.setSelectedModel(pair.first, pair.second)
                if (PermissionsFragment.hasPermissions(requireContext())) {
                    findNavController().navigate(R.id.action_menu_to_camera)
                } else {
                    findNavController().navigate(R.id.action_menu_to_permissions)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
