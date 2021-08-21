package com.azeupaul.globochat

import android.os.Bundle
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.preference.EditTextPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.google.android.material.snackbar.Snackbar


class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)

        // findPreference<Preference>(getString(R.string.key_account_settings))?.setOnPreferenceClickListener { true }

        val accountSettingPref = findPreference<Preference>(getString(R.string.key_account_settings))

        accountSettingPref?.setOnPreferenceClickListener {
            val navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_frag) as NavHostFragment
            val navController = navHostFragment.navController
            val action = SettingsFragmentDirections.actionSettingsToAccSettings()
            navController.navigate(action)
            true
        }
        
        val statusPref = findPreference<EditTextPreference>(getString(R.string.key_status))
        statusPref?.setOnPreferenceChangeListener { _, newValue ->
            val newStatus = newValue as String
            if (newStatus.contains("bad")){
                Toast.makeText(context, "Inappropriate status. Please read our privacy policy", Toast.LENGTH_LONG).show()

                false
            }else{
                true
            }
        }
    }

}