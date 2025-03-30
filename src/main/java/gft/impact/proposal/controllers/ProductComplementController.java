package gft.impact.proposal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gft.impact.proposal.model.ProductParameter;
import gft.impact.proposal.service.ProductComplementService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/product-complements")
public class ProductComplementController {

    @Autowired
    private ProductComplementService service;

    @PostMapping
    public ResponseEntity<ProductParameter> createProductComplement(@Valid @RequestBody ProductParameter productComplement) {
        ProductParameter createdProductComplement = service.createProductComplement(productComplement);
        return new ResponseEntity<>(createdProductComplement, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductParameter> getProductComplement(@PathVariable String id) {
        return service.getProductComplement(id)
                .map(productComplement -> new ResponseEntity<>(productComplement, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductParameter> updateProductComplement(@PathVariable String id, @Valid @RequestBody ProductParameter productComplement) {
        ProductParameter updatedProductComplement = service.updateProductComplement(id, productComplement);
        return new ResponseEntity<>(updatedProductComplement, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductComplement(@PathVariable String id) {
        service.deleteProductComplement(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
