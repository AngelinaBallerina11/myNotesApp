package com.angelinaandronova.mobile_ui.injection.module

import com.angelinaandronova.domain.executor.PostExecutionThread
import com.angelinaandronova.mobile_ui.browse.BrowseActivity
import com.angelinaandronova.mobile_ui.UiThread
import com.angelinaandronova.mobile_ui.detail.NoteDialogFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UiModule {

    @Binds
    abstract fun bindPostExecutionThread(uiThread: UiThread): PostExecutionThread

    @ContributesAndroidInjector
    abstract fun contributesBrowseActivity(): BrowseActivity

    @ContributesAndroidInjector
    abstract fun contributesDetailFragment(): NoteDialogFragment
}
