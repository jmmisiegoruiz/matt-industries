import {InMemoryDbService, RequestInfo, STATUS} from 'angular-in-memory-web-api';
import {Injectable} from '@angular/core';

@Injectable({
    providedIn: 'root',
})
export class InMemoryDataService implements InMemoryDbService {
    createDb() {
        const users = [
            {
                id: 11,
                userAuthentication: {
                    details: {
                        name: 'Pepe',
                    }
                }
            },
        ];

        return {
            users: users,
        };
    }

    // HTTP POST interceptor
    post(reqInfo: RequestInfo) {
        const collectionName = reqInfo.collectionName;
        if (collectionName == 'logout') {
            return reqInfo.utils.createResponse$(function () {
                return {
                    status: STATUS.OK
                }
            });
        } else {
            return undefined; // let the default POST handle all others
        }
    }
}