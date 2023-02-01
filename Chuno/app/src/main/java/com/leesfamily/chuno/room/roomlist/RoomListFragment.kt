package com.leesfamily.chuno.room.roomlist

import android.app.DatePickerDialog
import android.content.res.Resources.Theme
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.leesfamily.chuno.R
import com.leesfamily.chuno.databinding.FragmentRoomListBinding
import com.leesfamily.chuno.room.placeholder.PlaceholderContent
import com.leesfamily.chuno.util.custom.*

/**
 * 게임을 위한 방의 목록을 보여주는 Fragment이다.
 **/
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class RoomListFragment : Fragment(), CreateRoomDialogInterface {
    lateinit var binding: FragmentRoomListBinding
    private var param1: String? = null
    private var param2: String? = null
    private var columnCount = 1
    private lateinit var viewModel: RoomItemViewModel

    private lateinit var dialog1: CreateRoomDialog1
    private lateinit var dialog2: CreateRoomDialog2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[RoomItemViewModel::class.java]
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRoomListBinding.inflate(inflater, container, false)
        binding.toolbarInclude.toolbarTitle.text = getString(R.string.room_list_title)
        binding.myRoomList.apply {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(requireContext())
                else -> GridLayoutManager(requireContext(), columnCount)
            }
            adapter = RoomItemRecyclerViewAdapter(PlaceholderContent.ITEMS)
        }
        binding.allRoomList.apply {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(requireContext())
                else -> GridLayoutManager(requireContext(), columnCount)
            }
            adapter = RoomItemRecyclerViewAdapter(PlaceholderContent.ITEMS)

        }
        binding.refreshLayout.apply {
            setOnRefreshListener {
                // 새로고침 코드를 작성
                Log.d(TAG, "onCreateView: refreshing recyclerview")

                // 새로고침 완료시, 새로고침 아이콘이 사라질 수 있게 isRefreshing = false
                this.isRefreshing = false
            }

        }

        val fab: View = binding.createRoom
        fab.setOnClickListener { view ->
            val childFragmentManager = childFragmentManager
            childFragmentManager.findFragmentByTag("createRoomDialog1")?.let {
                childFragmentManager.beginTransaction().remove(it)
            }
            dialog1 = CreateRoomDialog1(requireContext(), this).apply {
                show(childFragmentManager, "createRoomDialog1")
            }

        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {
        private const val TAG = "추노_RoomListFragment"
        const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RoomListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }

    override fun onNextButtonClicked(view: View) {
        val childFragmentManager = childFragmentManager
        childFragmentManager.findFragmentByTag("createRoomDialog1")?.let {
            childFragmentManager.beginTransaction().remove(it)
        }
        dialog2 = CreateRoomDialog2(requireContext(), this).apply {
            show(childFragmentManager, "createRoomDialog2")
        }
    }

    override fun onPrevButtonClicked(view: View) {
        dialog1.show(childFragmentManager, "createRoomDialog1")
    }

}