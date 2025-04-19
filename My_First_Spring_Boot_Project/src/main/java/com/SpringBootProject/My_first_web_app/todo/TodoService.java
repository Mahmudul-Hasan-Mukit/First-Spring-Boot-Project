package com.SpringBootProject.My_first_web_app.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Component
@Service
public class TodoService {
	private static List<Todo> todos = new ArrayList<>();
	private static int todosCount=3;
	static {
		todos.add(new Todo(1, "Mukit", "learn Aws", LocalDate.now().plusYears(1), false));
		todos.add(new Todo(2, "Mukit", "learn Devops", LocalDate.now().plusYears(2), false));
		todos.add(new Todo(3, "Mukit", "learn FullStack", LocalDate.now().plusYears(3), false));
		todos.add(new Todo(4, "Mukit", "Crack Google", LocalDate.now().plusYears(4), false));
	}

	public List<Todo> findByUsername(String username) {
		List<Todo>newtodo=new ArrayList<>();
		for(int i=0;i<todos.size();i++) {
			Todo t=todos.get(i);
			if(username.equals(t.getUsername())) {
				newtodo.add(t);
			}
		}
		return newtodo;
	}

	public void addtodo(String username,String description
			,LocalDate targetDate,boolean done) {
		
     Todo todo=new Todo(++todosCount,username,description,targetDate,done);
      todos.add(todo);
	}
	public void deleteById(int id) {
		for(int i=0;i<todos.size();i++) {
			Todo n=todos.get(i);
			if(id==n.getId()) {
				todos.remove(i);
				break;
			}
		}
	}
	public void updateById(int id) {
		for(int i=0;i<todos.size();i++) {
			Todo n=todos.get(i);
			if(id==n.getId()) {
				String username=n.getUsername();
			    String des="Learn whatever you want";
			    LocalDate targetDate=n.getTargetDate();
			    boolean done=n.isDone();
				todos.remove(i);
				Todo todo=new Todo(n.getId(), username,des,targetDate,done);
			      todos.add(i,todo);;
				break;
			}
		}
	}

	public Todo findById(int id) {
		for(int i=0;i<todos.size();i++) {
			Todo n=todos.get(i);
			if(id==n.getId()) {
				return n;
			}
		}
		return null;
	}

	public void updateTodo(@Valid Todo todo) {
		// TODO Auto-generated method stub
		deleteById(todo.getId());
		todos.add(todo);
	}
	
}
