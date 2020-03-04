using UnityEngine;
using System.Collections;

public class ProjectileBehaviour : MonoBehaviour {

	// Use this for initialization
	public float damage ;
	
	public void Hit(){
		Destroy(gameObject);
	}
	
	public float GetDamage(){
		return damage;
	}
}
