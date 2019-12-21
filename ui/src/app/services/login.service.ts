import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {catchError} from "rxjs/operators";

export interface User {
    authenticated: boolean;
    userAuthentication: {
        details: {
            name: string,
        }
    }
}

@Injectable({
    providedIn: 'root',
})
export class LoginService {

    user$: Observable<User> = this.http.get<User>('/me')
        .pipe(
            catchError(err => {
                console.error(err);
                return throwError('Sth went wrong');
            })
        );

    logout$: Observable<void> = this.http.post<void>('/logout', null);

    constructor(private http: HttpClient) {
    }
}
