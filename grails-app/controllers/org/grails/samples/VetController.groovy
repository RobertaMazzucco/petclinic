package org.grails.samples

import org.springframework.dao.DataIntegrityViolationException

class VetController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [vetInstanceList: Vet.list(params), vetInstanceTotal: Vet.count()]
    }

    def create() {
        [vetInstance: new Vet(params)]
    }

    def save() {
        def vetInstance = new Vet(params.vet)
        if (!vetInstance.save(flush: true)) {
            render(view: "create", model: [vetInstance: vetInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'nuovo'), message(code: 'vet.label', default: 'Vet')])
        redirect(action: "show", id: vetInstance.id)
    }

    def show(Long id) {
        def vetInstance = Vet.get(id)
        if (!vetInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'vet.label', default: 'Vet'), id])
            redirect(action: "list")
            return
        }

        [vetInstance: vetInstance]
    }

    def edit(Long id) {
        def vetInstance = Vet.get(id)
        if (!vetInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'vet.label', default: 'Vet'), id])
            redirect(action: "list")
            return
        }

        [vetInstance: vetInstance]
    }

    def update(Long id, Long version) {
        def vetInstance = Vet.get(id)
        if (!vetInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'vet.label', default: 'Vet'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (vetInstance.version > version) {
                vetInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'vet.label', default: 'Vet')] as Object[],
                        "Another user has updated this Vet while you were editing")
                render(view: "edit", model: [vetInstance: vetInstance])
                return
            }
        }

        vetInstance.properties = params

        if (!vetInstance.save(flush: true)) {
            render(view: "edit", model: [vetInstance: vetInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'vet.label', default: 'Vet'), vetInstance.id])
        redirect(action: "show", id: vetInstance.id)
    }

    def delete(Long id) {
        def vetInstance = Vet.get(id)
        if (!vetInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'vet.label', default: 'Vet'), id])
            redirect(action: "list")
            return
        }

        try {
            vetInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'vet.label', default: 'Vet'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'vet.label', default: 'Vet'), id])
            redirect(action: "show", id: id)
        }
    }
}
