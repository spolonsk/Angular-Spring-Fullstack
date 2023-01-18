import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private baseUrl = "http://localhost:8080/api/v1/"

  constructor(private httpCLient:HttpClient) { }

 
  onLogin(obj:any):Observable<any>{
    return this.httpCLient.post(`${this.baseUrl}`+'auth/authenticate',obj);
  }
}
