export default function SignUp() {
  const link = import.meta.env.PROD
    ? "/oauth2/authorization/github"
    : "http://localhost:8080/oauth2/authorization/github";

  return (
    <div className="mx-auto flex h-72 items-center justify-center">
      <button className="flex items-center h-max rounded-lg px-5 py-4 text-xl font-light">
        <img className="h-8 mr-3" src="/github-mark.png" alt="GitHub" />
        <a href={link}>Sign up with GitHub</a>
      </button>
    </div>
  );
}
