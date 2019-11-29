import {Component, OnInit} from '@angular/core';
import {LoginService, User} from "../services/login.service";
import {EMPTY} from "rxjs";
import {catchError} from "rxjs/operators";

@Component({
    selector: 'app-product-list',
    templateUrl: './product-list.component.html',
    styleUrls: ['./product-list.component.css'],
    //changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ProductListComponent implements OnInit{

    user: User;
    error: string;

    constructor(
        private loginService: LoginService) {
    }

    logout() {
        this.loginService.logout$.subscribe(() => this.user = undefined);
    }

    ngOnInit(): void {
        this.loginService.user$
            .pipe(
                catchError(err => {
                    this.error = err;
                    return EMPTY;
                })
            ).subscribe(user => this.user = user);
    }
}