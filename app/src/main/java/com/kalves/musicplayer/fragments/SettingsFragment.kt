package com.kalves.musicplayer.fragments


import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.os.Bundle
import android.preference.*
import android.provider.Settings
import android.text.TextUtils
import android.util.Log
import com.kalves.musicplayer.R
import com.kalves.musicplayer.Utils.Prefs

// TODO: Rename parameter arguments, choose names that match

class SettingsFragment : PreferenceFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.preferences)

        bindPreferenceSummaryToValue(findPreference("currentUserName"))
        bindPreferenceSummaryToValue(findPreference("currentUserEmail"))
        bindPreferenceSummaryToValue(findPreference("currentUserNickname"))
        val wifisettings = findPreference("wifisettings")
        wifisettings.onPreferenceClickListener = Preference.OnPreferenceClickListener {
            val intent = Intent(Settings.ACTION_WIFI_SETTINGS)
            startActivity(intent)
            true
        }
        val bluetoothsettings = findPreference("bluetoothsettings")
        bluetoothsettings.onPreferenceClickListener = Preference.OnPreferenceClickListener {
            val intent = Intent(Settings.ACTION_BLUETOOTH_SETTINGS)
            startActivity(intent)

            true
        }
    }

    /**
     * A preference value change listener that updates the preference's summary
     * to reflect its new value.
     */
    private val sBindPreferenceSummaryToValueListener = Preference.OnPreferenceChangeListener { preference, value ->

        val stringValue = value.toString()

        if (preference is ListPreference) {
            // For list preferences, look up the correct display value in
            // the preference's 'entries' list.
            val index = preference.findIndexOfValue(stringValue)

            // Set the summary to reflect the new value.
            preference.setSummary(
                if (index >= 0)
                    preference.entries[index]
                else
                    null)

        } else if (preference is RingtonePreference) {
            // For ringtone preferences, look up the correct display value
            // using RingtoneManager.
            if (TextUtils.isEmpty(stringValue)) {
                // Empty values correspond to 'silent' (no ringtone).
                preference.setSummary("Silent")

            } else {
                val ringtone = RingtoneManager.getRingtone(
                    preference.getContext(), Uri.parse(stringValue))

                if (ringtone == null) {
                    // Clear the summary if there was a lookup error.
                    preference.setSummary(null)
                } else {
                    // Set the summary to reflect the new ringtone display
                    // name.
                    val name = ringtone.getTitle(preference.getContext())
                    preference.setSummary(name)
                }
            }
        }
        else {
            // For all other preferences, set the summary to the value's
            // simple string representation.
            preference.summary = stringValue
            var prefs: Prefs = Prefs(preference.context)
            Log.e("Preference Data", preference.key + " " + stringValue)
            prefs.setPreference(preference.key, stringValue)
            if(preference.key == "currentUserNickname"){
                if(stringValue.isBlank()){
                    prefs.setPreference(preference.key, prefs.currentUserName.split(' ')[0])
                }
                else {

                }
            }
        }
        true
    }

    /**
     * Binds a preference's summary to its value. More specifically, when the
     * preference's value is changed, its summary (line of text below the
     * preference title) is updated to reflect the value. The summary is also
     * immediately updated upon calling this method. The exact display format is
     * dependent on the type of preference.
     * @see .sBindPreferenceSummaryToValueListener
     */
    private fun bindPreferenceSummaryToValue(preference: Preference) {
        // Set the listener to watch for value changes.
        preference.onPreferenceChangeListener = sBindPreferenceSummaryToValueListener

        // Trigger the listener immediately with the preference's
        // current value.
        sBindPreferenceSummaryToValueListener.onPreferenceChange(preference,
            PreferenceManager
                .getDefaultSharedPreferences(preference.context)
                .getString(preference.key, ""))
    }
}
