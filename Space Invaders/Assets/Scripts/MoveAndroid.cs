using UnityEngine;
using System.Collections;
using System.Collections.Generic;

public class MoveAndroid : MonoBehaviour {

	public float speed = 10f;
	public GameObject Ship;
	public GameObject projectile;
	float LaserSpeed= 10f;
	
	
	private float ScreenWidth;
	// Use this for initialization
	void Start () {
		ScreenWidth = Screen.width;
	}
	
	// Update is called once per frame
	void Update () {
		TouchControl();
		
	}	
	void TouchControl(){
		int i= 0;
		//loop over every touch found
		while (i < Input.touchCount) {
			if ((Input.GetTouch (i).position.x > ScreenWidth /4) && (Input.GetTouch(i).position.x< ScreenWidth/2) ) {
				//move right
				Ship.transform.position += Vector3.right*speed*Time.deltaTime;
			}
			if (Input.GetTouch(i).position.x < ScreenWidth / 4) {
				//move left
				Ship.transform.position += Vector3.left*speed*Time.deltaTime;
				
			}
			if  ((Input.GetTouch(i).position.x > 3*ScreenWidth/4) && (Input.GetTouch(i).phase == TouchPhase.Ended)){
				GameObject Laser = Instantiate(projectile, Ship.transform.position,Quaternion.identity) as GameObject;
				Laser.rigidbody2D.velocity= new Vector3(0,LaserSpeed,0);
				Invoke("TouchShoot", 0.5f);		
			}
			++i;
			
		}
	}
//	void Right(){
//		if(Input.touchCount > 0 && Input.GetTouch(0).phase == TouchPhase.Moved)transform.position += Vector3.right*speed*Time.deltaTime;
//	}
//	void Left(){
//		if(Input.touchCount > 0 && Input.GetTouch(0).phase == TouchPhase.Moved)transform.position += Vector3.left*speed*Time.deltaTime;
//	}
//	void Shoot(){
//		if(Input.touchCount > 0 && Input.GetTouch(0).phase == TouchPhase.Moved){
//		GameObject Laser = Instantiate(projectile, transform.position,Quaternion.identity) as GameObject;
//		Laser.rigidbody2D.velocity= new Vector3(0,LaserSpeed,0);
//		}
//		Invoke("TouchShoot", 0.5f);
//	}
	
	
	
	
	
}
