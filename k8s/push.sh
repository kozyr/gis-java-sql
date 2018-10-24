set -e
die () {
    echo >&2 "$@"
    exit 1
}

[ "$#" -eq 2 ] || die "version and project id arguments required, $# provided"

VERSION=$1
PROJECT_ID=$2
docker build -t demo-gis:$VERSION .
docker tag demo-gis:$VERSION gcr.io/$PROJECT_ID/demo-gis:$VERSION
docker push gcr.io/$PROJECT_ID/demo-gis:$VERSION
