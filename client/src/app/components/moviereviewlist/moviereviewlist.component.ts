import { HttpParams } from '@angular/common/http';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Review } from 'src/app/models/model';
import { MovieService } from 'src/app/service/movie.service';

@Component({
  selector: 'app-moviereviewlist',
  templateUrl: './moviereviewlist.component.html',
  styleUrls: ['./moviereviewlist.component.css']
})
export class MoviereviewlistComponent implements OnInit, OnDestroy{

  review: Review[] = []

  constructor(private movieSvc: MovieService, private activeRoute: ActivatedRoute, private route: Router){ }

  ngOnInit(): void {

    this.getMovieReviews()
      .then(result => {
        this.review = result
      })
      
  }

  ngOnDestroy(): void {
      
  }

  getMovieReviews(): Promise<Review[]>{
    const value = this.activeRoute.snapshot.params["movieReview"]
    console.log(value)
    const params = new HttpParams().set("query", value)
    console.log(params)
    return this.movieSvc.getMovieReviews(params)
  }

  goBack(){
    this.route.navigate(["/search"])
  }


}
