export default function Login() {
  const link = import.meta.env.PROD ? "/oauth2/authorization/github" : "http://localhost:8080/oauth2/authorization/github";

  console.log(import.meta.env.PROD);
  console.log(import.meta.env.MODE);

  return <a href={link}>Login with GitHub</a>;
}
