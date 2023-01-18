import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Task } from '../../models/task';
import { TaskService } from 'src/app/services/task.service';
@Component({
  selector: 'app-create-task',
  templateUrl: './create-task.component.html',
  styleUrls: ['./create-task.component.css']
})
export class CreateTaskComponent implements OnInit {

  task: Task = new Task();

  constructor(private taskService: TaskService,
    private router: Router){}


  saveEmployee(){
    this.taskService.createTask(this.task).subscribe(data =>{
      console.log(data);
      this.goToEmployeeList
    },
    error=> console.log(error))
  }


  goToEmployeeList(){
    this.router.navigate(['./tasks']);

  }

  onSubmit(){
    this.saveEmployee();
    this.goToEmployeeList();
  }

  ngOnInit(): void {
  }

}
