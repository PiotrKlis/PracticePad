package com.piotr.practicepad.data.db

import com.piotr.practicepad.R

enum class ExerciseEnum(val title: String, val image: Int) {
    INVERTED_DOUBLES_8TH("8th inverted doublesx", R.drawable.inverted_eight),
    LEFT_HAND_8TH("8th left hand", R.drawable.left_hand_eight),
    RIGHT_HAND_8TH("8th right hand", R.drawable.right_hand_eight),
    DOUBLES_8TH("8th doubles", R.drawable.doubles_8th),
    TRIPLETS("Triplets", R.drawable.triplets_8th),
    SINGLE_STROKE_16TH("16th single stroke roll", R.drawable.single_16th),
    DOUBLES_16TH("16th doubles", R.drawable.doubles_16th),
    PARADIDDLE_16TH("16th paradiddle", R.drawable.single_paradiddle),
    SEXTUPLETS("Sextuplets", R.drawable.triplets_16th),
    SEXTUPLETS_PARADIDDLE("Paradiddle sextuplets", R.drawable.paradiddle_sixtuplets),
    SEXTUPLETS_PARADDIDLE_DIDDLE("Paradiddle-diddle sextuplets", R.drawable.single_paradiddle_diddle),
    SINGLE_STROKE_FOUR("Single stroke four", R.drawable.single_stroke_four),
    SINGLE_STROKE_SEVEN("Single stroke seven", R.drawable.single_stroke_seven),
    TRIPLETS_ONE_HAND_8TH("Triple stroke roll", R.drawable.triplets_one_hand),
    TRIPLE_STROKE_ROLL("Triple stroke roll sextuplets", R.drawable.triple_stroke_roll),
    SINGLE_ARMY_TRIPLET("Single army triplet", R.drawable.single_army_triplet),
    DRAG("Drag", R.drawable.drag),
    DRAG_PARADIDDLE("Drag paradiddle", R.drawable.drag_paradiddle),
    DRAG_PARADIDDLE_2("Drag paradiddle 2", R.drawable.drag_paradiddle_2),
    SINGLE_DRAG_TAP("Single drag tap", R.drawable.single_drag_tap),
    SINGLE_DRAGADIDDLE("Single dragadiddle", R.drawable.single_dragadiddle),
    FLAM_ACCENT("Flam accent", R.drawable.flam_accent),
    FLAM_DRAG("Flam drag", R.drawable.flam_drag),
    FLAM_PARADIDDLE("Flam paradiddle", R.drawable.flam_paradiddle),
    FLAM_TAP("Flam tap", R.drawable.flam_tap),
    FLAM("Flam", R.drawable.flam),
    PATAFLAFLA("Pataflala", R.drawable.pataflafla),
    SINGLE_FLAMMED_MILL("Single Flammed Mill", R.drawable.single_flammed_mill),
    QUARTER("Quarters", R.drawable.quarter)
}

data class ExerciseData(val time: Long, val exerciseData: ExerciseEnum)

