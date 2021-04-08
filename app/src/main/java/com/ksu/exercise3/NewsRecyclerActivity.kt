package com.ksu.exercise3

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.ksu.exercise3.adapter.NewsRecyclerAdapter
import com.ksu.exercise3.adapter.NewsRecyclerAdapter.ClickItemListener
import com.ksu.exercise3.dto.NewsDTO
import com.ksu.exercise3.dto.ResponseDTO
import com.ksu.exercise3.network.Controller
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_news_recycler.*
import retrofit2.Response
import java.io.IOException

class NewsRecyclerActivity : AppCompatActivity(), ClickItemListener {
    private var newsRecyclerAdapter: NewsRecyclerAdapter? = null

    private var popup: PopupMenu? = null
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_recycler)
        newsRecyclerAdapter = NewsRecyclerAdapter(this, this, Glide.with(this))
        val appBarLayout = findViewById<Toolbar>(R.id.toolbar_activity_news)
        appBarLayout.setOnMenuItemClickListener { item: MenuItem? -> onOptionsItemSelected(item!!) }
        recycler.adapter = newsRecyclerAdapter
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            recycler.layoutManager = LinearLayoutManager(this)
        } else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            recycler.layoutManager = GridLayoutManager(this, 2)
        }
        recycler.addItemDecoration(DividerItemDecoration(recycler.context, DividerItemDecoration.VERTICAL))
        about.setOnClickListener(View.OnClickListener { startAbout() })
        categoryMenu.setOnClickListener(View.OnClickListener {
            popup = PopupMenu(this@NewsRecyclerActivity, categoryMenu)
            menuViewed(popup)
            popup?.setOnMenuItemClickListener { item ->
                loadNews(item.title.toString())
                true
            }
            popup!!.show()
        })
    }

    private fun menuViewed(popup: PopupMenu?) {
        //List<String> categoryList = newsRecyclerAdapter.getCategoryNews();
        popup?: return
        popup.menuInflater
                .inflate(R.menu.menu_category, popup.menu)

//        for(int i = 0; i < categoryList.size(); i++)
//            popup.getMenu().add(categoryList.get(i));
        popup.menu.add("1")
        popup.menu.add("7")
        popup.menu.add("30")
    }

    fun startAbout() {
        val intent = Intent(this, AboutActivity::class.java)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        loadNews("7")
    }

    override fun clickOnItem(news: NewsDTO?) {
        if (news != null) {
            FullNewsActivity.start(this, news)
        }
    }

    private fun loadNews(period: String) {
        val searchDisposable = Controller.instance
                ?.news()
                ?.period(period)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({ response: Response<ResponseDTO?>? -> checkResponseAndShowState(response) }) { throwable: Throwable -> handleError(throwable) }
        if (searchDisposable != null) {
            compositeDisposable.add(searchDisposable)
        }
    }

    private fun handleError(throwable: Throwable) {
        throwable.printStackTrace()
        if (throwable is IOException) {
            return
        }
    }

    private fun checkResponseAndShowState(response: Response<ResponseDTO?>?) {
        val body = response?.body()
        newsRecyclerAdapter!!.replaceItems(body!!)
        Log.d("Test1", "replaceitems")
        progressBar!!.visibility = View.GONE
    }
}