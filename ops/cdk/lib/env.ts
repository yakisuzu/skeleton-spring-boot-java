export class Dev implements Stage {
  constructor(s: string) {
    super(s)
  }
}

export class Stage {
  resourceName: string
  cfName: string

  constructor(s: string) {
    this.resourceName = s.toLowerCase()
    this.cfName = this.resourceName.replace(/^./, v => v.toUpperCase())
  }

  static of(s: string): Stage | undefined {
    switch (s) {
      case 'prd':
        return Prd(s)
      case 'stg':
        return Stg(s)
      case 'dev':
        return new Dev(s)
      default:
        return undefined
    }
  }
}

''
