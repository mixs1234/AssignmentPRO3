package dk.via.course_assignment_2.service;

import dk.via.course_assignment_2.grpc.*;
import dk.via.course_assignment_2.model.Animal;
import dk.via.course_assignment_2.model.Product;
import io.grpc.protobuf.StatusProto;
import dk.via.course_assignment_2.business.SlaughterhouseSystem;
import dk.via.course_assignment_2.business.persistence.NotFoundException;
import dk.via.course_assignment_2.business.persistence.PersistenceException;
import dk.via.course_assignment_2.business.persistence.ValidationException;
import com.google.rpc.Status;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.Collection;

@GrpcService
public class SlaughterhouseServiceImpl extends SlaughterhouseServiceGrpc.SlaughterhouseServiceImplBase {

    private final SlaughterhouseSystem system;

    public SlaughterhouseServiceImpl(SlaughterhouseSystem system) {
        this.system = system;
    }

    @Override
    public void retrieveAllAnimalIDsFromProduct(ProductNumber request, StreamObserver<AnimalsRegNumberInProduct> responseObserver) {
        String animalRegNumber = request.getProductRegNumber();
        try {
            Collection<Animal> animals = system.retrieveRegNumberFromAnimalsInProduct(animalRegNumber);
            AnimalsRegNumberInProduct.Builder responseBuilder = AnimalsRegNumberInProduct.newBuilder();
            for (Animal animal : animals) {
                responseBuilder.addAnimalRegNumbers(animal.getRegNumber());
            }
            AnimalsRegNumberInProduct response = responseBuilder.build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (NotFoundException | ValidationException e) {
            Status errorStatus = Status.newBuilder()
                    .setMessage("Not found or invalid input")
                    .build();
            responseObserver.onError(StatusProto.toStatusRuntimeException(errorStatus));
        } catch (PersistenceException e) {
            Status errorStatus = Status.newBuilder()
                    .setMessage("Internal error")
                    .build();
            responseObserver.onError(StatusProto.toStatusRuntimeException(errorStatus));
        }catch (Exception e) {
            Status errorStatus = Status.newBuilder()
                    .setMessage("Unexpected error occurred: " + e.getMessage())
                    .build();
            responseObserver.onError(StatusProto.toStatusRuntimeException(errorStatus));
        }
    }

    @Override
    public void retrieveAllProductIDsFromAnimal(AnimalRegNumber request, StreamObserver<ProductsRegNumberInAnimal> responseObserver) {
        String productRegNumber = request.getAnimalRegNumber();
        try {
            Collection<Product> products = system.retrieveAllProductsFromAnimal(productRegNumber);
            ProductsRegNumberInAnimal.Builder responseBuilder = ProductsRegNumberInAnimal.newBuilder();
            for(Product product : products) {
                responseBuilder.addProductRegNumbers(product.getProductID());
            }
            ProductsRegNumberInAnimal response = responseBuilder.build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (NotFoundException | ValidationException e) {
            Status errorStatus = Status.newBuilder()
                    .setMessage("Not found or invalid input")
                    .build();
            responseObserver.onError(StatusProto.toStatusRuntimeException(errorStatus));
        } catch (PersistenceException e) {
            Status errorStatus = Status.newBuilder()
                    .setMessage("Internal error")
                    .build();
            responseObserver.onError(StatusProto.toStatusRuntimeException(errorStatus));
        }catch (Exception e) {
            Status errorStatus = Status.newBuilder()
                    .setMessage("Unexpected error occurred: " + e.getMessage())
                    .build();
            responseObserver.onError(StatusProto.toStatusRuntimeException(errorStatus));
        }
    }

}