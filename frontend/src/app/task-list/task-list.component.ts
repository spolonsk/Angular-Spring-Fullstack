import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Task } from '../task';
import { TaskService } from '../task.service';

@Component({
  selector: 'app-task-list',
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.css']
})
export class TaskListComponent implements OnInit{

  tasks!: Task[];

  constructor(private taskService: TaskService,
    private router:Router){ }

  private getTasks(){
    this.taskService.getTaskList().subscribe(data =>{
      this.tasks = data;
    });
  }

  deleteTask(id:number){
    this.taskService.deleteTask(id).subscribe(data=>{
      this.getTasks();
    })
  }

  ngOnInit(): void {
    this.getTasks();
  }

}
