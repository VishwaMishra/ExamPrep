import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { UserService } from '../../services/user.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import Swal from 'sweetalert2';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-sign-up',
  standalone: true,
  imports: [MatInputModule,
    MatFormFieldModule,
    FormsModule
  ],
  templateUrl: './sign-up.component.html',
  styleUrl: './sign-up.component.css'
})
export class SignUpComponent implements OnInit {

  constructor(private _userService: UserService, private snack:MatSnackBar) {}

  public user={
    username:'',
    password:'',
    firstname:'',
    lastname:'',
    email:'',
    phone:''
  }

  ngOnInit():void {}


  formSubmit(){
    console.log(this.user);

    if(this.user.username=='' || this.user.username == null){
      this.snack.open("Username is required",'',{
        duration:3000,
        verticalPosition:'top'
      });
      //alert('Username is required!!');
      return;
    }

    if(this.user.firstname=='' || this.user.firstname == null){
      alert('firstname is required!!');
      return;
    }
    
    if(this.user.email=='' || this.user.email == null){
      alert('Email is required!!');
      return;
    }

    if(this.user.lastname=='' || this.user.lastname == null){
      alert('lastname is required!!');
      return;
    }

    if(this.user.phone=='' || this.user.phone == null){
      alert('Username is required!!');
      return;
    }

    //addUser: userservice
    // this._userService.addUser(this.user).subscribe(
    //   (data)=>{
    //     console.log(data);
    //     //alert('success');
    //     Swal.fire('Sucess','User Successfully is Registered');
    //   },
    //   (error)=>{
    //     console.log(error);
    //     //alert('Something went wrong');
    //     this.snack.open('Something went wrong','',{
    //       duration:3000
    //     })
    //   }
    // )

  }
}
