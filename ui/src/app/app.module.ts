import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HttpClientModule, HttpClientXsrfModule} from '@angular/common/http';
import {HttpClientInMemoryWebApiModule} from 'angular-in-memory-web-api';
import {InMemoryDataService} from './services/in-memory-data.service';
import {MatButtonModule} from '@angular/material/button';
import {MatToolbarModule} from '@angular/material/toolbar';
import {RouterModule} from '@angular/router';
import {ReactiveFormsModule} from '@angular/forms';

import {AppComponent} from './app.component';
import {ProductListComponent} from './product-list/product-list.component';
import {environment} from "../environments/environment";

@NgModule({
    imports: [
        BrowserModule,
        HttpClientModule,
        environment.useMockServer ?   HttpClientInMemoryWebApiModule.forRoot(InMemoryDataService, {
            delay: 1000,
            passThruUnknownUrl: true
        }) : [],
        HttpClientXsrfModule,
        MatButtonModule,
        MatToolbarModule,
        ReactiveFormsModule,
        RouterModule.forRoot([
            {path: '', component: ProductListComponent},
        ]),
        BrowserAnimationsModule
    ],
    declarations: [
        AppComponent,
        ProductListComponent
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}


/*
Copyright Google LLC. All Rights Reserved.
Use of this source code is governed by an MIT-style license that
can be found in the LICENSE file at http://angular.io/license
*/