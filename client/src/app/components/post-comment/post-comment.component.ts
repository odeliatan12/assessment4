import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Comments } from 'src/app/models/model';
import { MovieService } from 'src/app/service/movie.service';

@Component({
  selector: 'app-post-comment',
  templateUrl: './post-comment.component.html',
  styleUrls: ['./post-comment.component.css']
})
export class PostCommentComponent implements OnInit {

  form!: FormGroup
  movieName!: ""
  comment!: Comment
  requirementsMet: boolean = false;
  isFormValid = false;

  constructor(private fb: FormBuilder,private route: Router, private movieSvc: MovieService, private activeRoute: ActivatedRoute){ }

  ngOnInit(): void {

    this.form = this.createCommentForm();
    this.movieName = this.activeRoute.snapshot.params["movieName"]
      
  }

  createCommentForm(): FormGroup{
    return this.fb.group({
      name: this.fb.control<string>('', [ Validators.required, Validators.min(3) ]),
      rating: this.fb.control<number>( 1, [ Validators.required, Validators.max(5), Validators.min(1) ]),
      comment: this.fb.control<string>('', [ Validators.required ])
    })
  }

  insertComment(){

    const movieName = this.activeRoute.snapshot.params["movieName"];
    const name = this.form?.value["name"];
    const rating = this.form?.value["rating"];
    const comment = this.form?.value["comment"];
    const c = {} as Comments;
    c.movieName = movieName
    c.name = name
    c.rating = rating
    c.comment = comment
    console.log(c)
    this.movieSvc.insertComments(c);
    this.route.navigate(["/"])
  }

  goBack(){
    this.form.reset()
    this.route.navigate(["/"])
  }

  onFormChange(){
    this.isFormValid = this.form.valid
  }


}
