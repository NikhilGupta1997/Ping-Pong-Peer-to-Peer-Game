		package Model;

import java.util.*;

public class Board
{
	/** Back end Board class that holds all the data. **/
	private float dim_x;
	private float dim_y;
	private int no_balls;
	private ArrayList<Ball> vector_balls;
	private ArrayList<RandomObj> objects;
	private int no_paddles;
	private ArrayList<Paddle> vector_paddles;
	private ArrayList<Boolean> dead_Alive;

	public Board()
	{
		dim_x = 600;
		dim_y = 600;
		no_balls = 0;
		vector_balls = new ArrayList<Ball>();
		no_paddles = 0;
		vector_paddles = new ArrayList<Paddle>();
		dead_Alive = new ArrayList<Boolean>();
		objects = new ArrayList<RandomObj>();
	}

	public 	ArrayList<Ball> getBalls()
	{
		return vector_balls;
	}

	public ArrayList<Paddle> getPaddles()
	{
		return vector_paddles;
	}

	public ArrayList<RandomObj> getObjects()
	{
		return objects;
	}

	public void addBall(Ball b)
	{
		vector_balls.add(b);
	}

	public void removeBall(int i)
	{
		vector_balls.remove(i);
		dead_Alive.remove(i);
	}

	public void addPaddle(Paddle p)
	{
		vector_paddles.add(p);
		dead_Alive.add(true);
	}

	public void removePaddle(int i)
	{
		vector_paddles.remove(i);
	}

	public void addObject(RandomObj r)
	{
		objects.add(r);
	}

	public void removeObj(int i)
	{
		objects.remove(i);
	}

	public void movePaddle(int i, double new_px,double new_py,double new_len,int times_missed, boolean life)
	{
		Paddle p = new Paddle(new_len, new_px, new_py, times_missed, life);
		vector_paddles.set(i,p);
	}

	public void moveBall(int i,double vx, double vy, double cx, double cy, double r)
	{
		Ball b = new Ball(vx, vy, cx, cy, r);
		vector_balls.set(i, b);
	}

	public void moveObj(int i,double vx, double vy, double cx, double cy, boolean inc_len)
	{
		RandomObj r = new RandomObj(vx, vy, cx, cy, inc_len);
		objects.set(i,r);
	}

	public Paddle getIthpaddle(int i)
	{
		return vector_paddles.get(i);
	}

}