import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { firstValueFrom, Subject } from "rxjs";
import { Comments, Review, Search } from "../models/model";

@Injectable()
export class MovieService{

    getSearch = new Subject<Search>();

    constructor(private http: HttpClient){ }

    getMovieReviews(params: HttpParams): Promise<Review[]>{
        const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8')
        return firstValueFrom(
            this.http.get<Review[]>("/api/search", { params: params, headers: headers })
        )
    }

    insertComments(comment: Comments): Promise<Comments>{
        const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8')
        return firstValueFrom(
            this.http.post<Comments>("/api/comment", comment, { headers: headers})
        )
    }

}