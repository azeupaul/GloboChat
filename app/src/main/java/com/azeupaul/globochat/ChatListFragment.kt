package com.azeupaul.globochat

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.preference.PreferenceManager


class ChatListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val sharedPreference = PreferenceManager.getDefaultSharedPreferences(context)

        val status = sharedPreference.getString(getString(R.string.key_status), "")

        Log.i("ChatListFragment", "Status content: $status")

        val autoDownload = sharedPreference.getBoolean(getString(R.string.key_auto_download), false)

        Log.i("ChatListFragment", "AutoDownload: $autoDownload")

        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_chat_list, container, false)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.chat_list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_settings -> {
                val navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_frag) as NavHostFragment
                val navController = navHostFragment.navController
                val action = ChatListFragmentDirections.actionChatListToSettings()
                navController.navigate(action)
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}