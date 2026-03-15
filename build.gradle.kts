// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    // These point to your libs.versions.toml file
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false

    // Modern way to include Google Services (replaces the buildscript block)
    alias(libs.plugins.google.services) apply false}

// buildscript block is removed to prevent "duplicate plugin" errors.
