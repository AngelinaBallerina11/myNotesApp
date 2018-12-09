package com.angelinaandronova.mobile_ui.injection.module

import com.angelinaandronova.data.NotesDataRepository
import com.angelinaandronova.domain.repository.NotesRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun bindDataRepository(dataRepository: NotesDataRepository): NotesRepository
}
