package com.kalves.musicplayer.fragments

import android.content.Context
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.kalves.musicplayer.R
import kotlinx.android.synthetic.main.menu_layout.*
import kotlinx.android.synthetic.main.menu_item.view.*

// TODO: Customize parameter argument names
const val ARG_MENU_OPTIONS_ID = "item_count"

/**
 *
 * A fragment that shows a list of items as a modal bottom sheet.
 *
 * You can show this modal bottom sheet from your activity like this:
 * <pre>
 *    ApplicationMenu.newInstance(30).show(supportFragmentManager, "dialog")
 * </pre>
 *
 * You activity (or fragment) needs to implement [ApplicationMenu.Listener].
 */
class ApplicationMenu : BottomSheetDialogFragment() {
    private var mListener: Listener? = null
    private val icons: List<Int> = listOf(
        R.drawable.ic_play_24dp,
        R.drawable.ic_library_music_24dp,
        R.drawable.ic_settings_24dp
    )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.menu_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        list.layoutManager = LinearLayoutManager(context)
        list.adapter = MenuAdapter(arguments!!.getInt(ARG_MENU_OPTIONS_ID))
        super.onViewCreated(view, savedInstanceState)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        val parent = parentFragment
        mListener = if (parent != null) {
            parent as Listener
        } else {
            context as Listener
        }
    }

    override fun onDetach() {
        mListener = null
        super.onDetach()
    }

    interface Listener {
        fun onMenuClicked(option: CharSequence)
    }

    private inner class ViewHolder internal constructor(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.menu_item, parent, false)) {

        internal val text: TextView = itemView.text
        internal val icon: ImageView = itemView.icon

        init {
            text.setOnClickListener {
                mListener?.let {
                    it.onMenuClicked(text.text)
                    dismiss()
                }
            }
        }
    }

    private inner class MenuAdapter internal constructor(private val id: Int) :
        RecyclerView.Adapter<ViewHolder>() {
        val options = resources.getStringArray(id)
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(parent.context), parent)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.text.text = options[position]
            holder.icon.setImageDrawable(resources.getDrawable(icons[position]))
        }

        override fun getItemCount(): Int {
            return resources.getStringArray(id).size
        }
    }

    companion object {

        // TODO: Customize parameters
        fun newInstance(menuOptionsId: Int): ApplicationMenu =
            ApplicationMenu().apply {
                arguments = Bundle().apply {
                    putInt(ARG_MENU_OPTIONS_ID, menuOptionsId)
                }
            }

    }
}
