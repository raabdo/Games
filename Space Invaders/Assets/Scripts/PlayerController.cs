using UnityEngine;
using System.Collections;
using UnityEngine.UI;

public class PlayerController : MonoBehaviour {
	// Use this for initialization
	float xmin, xmax;
	public float health ;
	public GameObject projectile;	
	public float LaserSpeed ;
	public float FireRate;
	public AudioClip Firesound, Death;
	public float Padding = 1f;
	private Score score;
	public Image Healthbar;
	ProjectileBehaviour Laser; 
	float maxhealth ;
	
	void OnTriggerEnter2D(Collider2D col){
		Laser = col.gameObject.GetComponent<ProjectileBehaviour>();
		audio.Play();
		if (Laser){
			health -= Laser.GetDamage(); 
			Laser.Hit();
			if (health <=0){
				Die();
			}
			UpdateHealth();
		}	
	}
	void Start () {
		maxhealth = health;
		float distance = transform.position.z - Camera.main.transform.position.z; 
		Vector3 Leftmost = Camera.main.ViewportToWorldPoint(new Vector3(0,0,distance));
		Vector3 Rightmost = Camera.main.ViewportToWorldPoint(new Vector3(1,0,distance));
		xmin = Leftmost.x +Padding;
		xmax= Rightmost.x - Padding;
		}
	
	// Update is called once per frame
	void Update () {
		Move ();
		if (Input.GetKeyDown(KeyCode.Space)){
			InvokeRepeating("Shoot", 0.000001f, FireRate);
		}
		if (Input.GetKeyUp(KeyCode.Space)){
			CancelInvoke("Shoot");
		}	
	}
	void Shoot(){
		GameObject Laser = Instantiate(projectile,transform.position, Quaternion.identity) as GameObject;
		Laser.rigidbody2D.velocity = new Vector3 (0,LaserSpeed,0);
		AudioSource.PlayClipAtPoint(Firesound, transform.position);
	}
	
	void UpdateHealth(){
		float ratio;
		if (health <= 0){
			ratio = 0;
		}
		else{
			ratio = health/maxhealth;
		}
		//Healthbar.rectTransform.localScale = new Vector3 (ratio , 1,1);
		Healthbar.fillAmount = ratio;
	}
	
	void Die(){
		Destroy(gameObject);
		AudioSource.PlayClipAtPoint(Death, transform.position);
		LevelManager L = GameObject.FindObjectOfType<LevelManager>();
		L.NextLevel();
		
	}
	
	void Move(){
		float speed=10f ;
		
		if(Input.GetKey(KeyCode.LeftArrow)){
			//transform.position += new Vector3(-speed*Time.deltaTime,0,0);
			transform.position += Vector3.left*speed*Time.deltaTime;
		} 
		else if(Input.GetKey(KeyCode.RightArrow) ){
		 transform.position += Vector3.right*speed*Time.deltaTime;
		}
		float PosX = Mathf.Clamp(transform.position.x,xmin ,xmax);
		transform.position= new Vector3(PosX,transform.position.y, transform.position.z);
	}
	
}
