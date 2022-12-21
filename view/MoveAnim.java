package view;

import Modele.Delivery;
import Modele.Company;
import Modele.Point;
import java.io.FileInputStream;
import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class MoveAnim {
	
	private ImageView picture;
	private Delivery deli;
	private Company company;
	private Point destination;
	private PathTransition pathTransition;
	private View view;
	
	/**
	 * Constructor of the class MoneAnim creat a PathTransition animation with the parameter.
	 * @param destination
	 * @param deli
	 * @param company
	 * @param view
	 * @throws Exception
	 */
	public MoveAnim(Point destination,Delivery deli,Company company,View view) throws Exception {
		this.company     = company;
		this.deli        = deli;
		this.picture     = new ImageView(new Image(new FileInputStream("src/" + this.deli.getTransport().getPicSource())));
		this.destination = destination;
		this.view        = view;

		//those line make the picture invisible in the case it appear befort the animation.
		this.picture.setFitHeight(1);
		this.picture.setFitWidth(1);

		Path path = new Path();
		//this line will move the picture a the coordinate indicated.
		path.getElements().add(new MoveTo((double) this.company.getCompanyLocation().getX(),(double)this.company.getCompanyLocation().getY()));
		//this line indicate that the picture will move in a straight line to the coordinate indicated. 
		path.getElements().add(new LineTo((double) this.destination.getX(),(double) this.destination.getY()));
		//this line initialise the PathTansition wich is the class that make the animation.
		PathTransition pathTransition = new PathTransition();
		pathTransition.setPath(path);
		//this line set the duration of the animation.
		pathTransition.setDuration(Duration.millis(((deli.getRunTime()*3600)*2))); 
		//this line set the node use by the animation with the picture. 
		pathTransition.setNode(this.picture);
		//this line set the orientation of the picture tangent to the trajectory.
		pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
		//this line indicate that the animation will be repeat but since the reverse is true the picture will do a round-trip.
		pathTransition.setCycleCount(2);
		//this line set the round-trip of the picture.
		pathTransition.setAutoReverse(true);
		this.pathTransition = pathTransition;
	}

	public void starMove() {
		//the picture is resized.
		this.picture.setFitHeight(50);
		this.picture.setFitWidth(50);
		//the animation is started..
		this.pathTransition.play();
		//the labels are refresh with the first delivery on the list.
		this.view.refreshLabel();
		// the employee and his vehicle are move the the busy list?
		this.view.getController().moveVecEmpToBusy(this.deli.getTransport(),this.deli.getDeliMan());
		
		this.pathTransition.setOnFinished(new EventHandler<>() {
			@Override
			public void handle(ActionEvent arg0) {
				//At the end of the animation the picture become to small to be seen, it's like removing it.
				MoveAnim.this.picture.setFitHeight(1);
				MoveAnim.this.picture.setFitWidth(1);
				MoveAnim.this.view.getController().moveVecEmpToFree(MoveAnim.this.deli.getTransport(),MoveAnim.this.deli.getDeliMan());
				MoveAnim.this.view.getController().getCompany().removeDelivery(deli);
				//and we refresh the information of the window.
				MoveAnim.this.view.refreshLabel();
			}
		});
	}

	
	// Getter :
	
	public ImageView getPicture() {
		return this.picture;
	}
	
	public PathTransition getPathTransition() {
		return this.pathTransition;
	}
}
